package im.actor.server.grouppre



import im.actor.api.rpc.grouppre.{ApiGroupPre, UpdateGroupPreCreated, UpdateGroupPreParentChanged, UpdateGroupPreRemoved}
import im.actor.api.rpc.groups.{ApiGroup, ApiGroupType}
import akka.pattern.pipe
import im.actor.server.GroupPreCommands.{ChangeOrder, ChangeOrderAck, ChangeParent, ChangeParentAck, Create, CreateAck, Remove, RemoveAck}
import im.actor.server.persist.UserRepo
import im.actor.server.persist.grouppre.{PublicGroup, PublicGroupRepo}
import im.actor.server.sequence.SeqState
import im.actor.util.misc.StringUtils
import org.joda.time.Instant

import scala.collection.IndexedSeq
import scala.concurrent.Future

/**
  * Created by 98379720172 on 03/02/17.
  */
private [grouppre] trait GroupPreCommandHandlers {

  this: GroupPreProcessor =>

  private def updateShortName(groupId:Int, name:String, userId:Int, authId:Long, interaction:Int) : Unit ={
    groupExt.updateShortName(groupId, userId, authId, Some(name)) onFailure {
      case e ⇒
        if(interaction < 5)
          updateShortName(groupId, s"$name$interaction", userId, authId, interaction + 1)
    }
  }

  protected def create(cmd: Create): Unit = {
    val createdAt = Instant.now
    val result: Future[CreateAck] = for {
      apiGroup <- groupExt.getApiStruct(cmd.groupId, cmd.userId)

      _ = updateShortName(apiGroup.id, StringUtils.createShortName(apiGroup.title), cmd.userId, cmd.authId, 0)

      nextOrder <- db.run(PublicGroupRepo.nextPosition(0))

      publicGroup = PublicGroup(id = apiGroup.id,
        typ = (apiGroup.groupType match {
          case Some(ApiGroupType.GROUP) => "G"
          case Some(ApiGroupType.CHANNEL) => "C"
          case _ => "G"
        }),
        order = nextOrder match {
          case Some(v) => {
            v+1
          }
          case None => {
            0
          }
        },
        accessHash = apiGroup.accessHash
      )

      _ <- db.run(
        (for {
          _ ← PublicGroupRepo.createOrUpdate(publicGroup)
        } yield ())
      )

      update = UpdateGroupPreCreated(ApiGroupPre(
        groupId = apiGroup.id,
        hasChildrem = false,
        acessHash = apiGroup.accessHash,
        order = publicGroup.order,
        parentId = Option(publicGroup.parentId)
      ))

      activeUsersIds <- db.run(UserRepo.activeUsersIds)
      seqState <- seqUpdExt.broadcastClientUpdate(cmd.userId, cmd.authId, activeUsersIds.toSet, update)
      
    }yield(CreateAck(Some(seqState)))

    result pipeTo sender() onFailure {
      case e ⇒
        log.error(e, "Failed to create group pre")
    }
  }

  protected def remove(cmd: Remove): Unit = {
    val result: Future[RemoveAck] = for {
      apiGroup <- groupExt.getApiStruct(cmd.groupId, cmd.userId)
      publicGroup <- db.run(PublicGroupRepo.findById(cmd.groupId))

      seqState:SeqState <- publicGroup match {
        case Some(pg) => {
          for{
            (removedChildrens, parentChildrens) <- db.run(for {
              _ <- PublicGroupRepo.delete(pg.id)
              removedChildrens <- PublicGroupRepo.childrenIds(pg.id)

              _ <- PublicGroupRepo.updateParentByOldParend(pg.id, pg.parentId)

              parentHasChildren <- PublicGroupRepo.possuiFilhos(pg.parentId)
              _ <- PublicGroupRepo.updateHasChildrenByParent(pg.parentId, parentHasChildren)

              parentChildrens <- PublicGroupRepo.childrenIds(pg.parentId)
            } yield (removedChildrens, parentChildrens))

            update = UpdateGroupPreRemoved(ApiGroupPre(
              groupId = apiGroup.id,
              hasChildrem = pg.hasChildrem,
              acessHash = apiGroup.accessHash,
              order = pg.order,
              parentId = Option(pg.parentId)
            ), removedChildrens.toIndexedSeq, parentChildrens.toIndexedSeq)

            activeUsersIds <- db.run(UserRepo.activeUsersIds)
            seqState <- seqUpdExt.broadcastClientUpdate(cmd.userId, cmd.authId, activeUsersIds.toSet, update)
          } yield seqState
        }
        case None =>{

          val update = UpdateGroupPreRemoved(ApiGroupPre(
            groupId = apiGroup.id,
            hasChildrem = false,
            acessHash = apiGroup.accessHash,
            order = 0,
            parentId = None
          ), IndexedSeq.empty, IndexedSeq.empty)

          for{
            activeUsersIds <- db.run(UserRepo.activeUsersIds)
            seqState <- seqUpdExt.broadcastClientUpdate(cmd.userId, cmd.authId, activeUsersIds.toSet, update)
          } yield seqState
        }
      }
    } yield (RemoveAck(Option(seqState)))

    result pipeTo sender() onFailure {
      case e ⇒
        log.error(e, "Failed to remove Group Pre")
    }
  }

  protected def changeParent(cmd: ChangeParent): Unit = {
    val result: Future[ChangeParentAck] = for{

      previous <- db.run(for{
        retorno <- PublicGroupRepo.findById(cmd.groupId)
        _ <- PublicGroupRepo.updateParent(cmd.groupId, cmd.parentId)
        _ ← PublicGroupRepo.updateHasChildrenByParent(cmd.parentId, true)
        hasChildren <- PublicGroupRepo.possuiFilhos(retorno.get.parentId)
        _ <- PublicGroupRepo.updateHasChildrenByParent(retorno.get.parentId, hasChildren)
      } yield(retorno))

      update = UpdateGroupPreParentChanged(cmd.groupId, cmd.parentId, previous.get.parentId)

      activeUsersIds <- db.run(UserRepo.activeUsersIds)
      seqState <- seqUpdExt.broadcastClientUpdate(cmd.userId, cmd.authId, activeUsersIds.toSet, update)
    } yield (ChangeParentAck(Some(seqState)))

    result pipeTo sender() onFailure {
      case e ⇒
        log.error(e, "Failed to Change GroupPre parent")
    }
  }

  protected def changeOrder(cmd: ChangeOrder): Unit = {
    val result: Future[ChangeOrderAck] = for{

    }
  }

}