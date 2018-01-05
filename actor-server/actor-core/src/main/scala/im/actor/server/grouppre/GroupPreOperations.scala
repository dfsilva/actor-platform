package im.actor.server.grouppre

import akka.pattern.ask
import akka.util.Timeout
import im.actor.server.{GroupPre}
import im.actor.server.GroupPreCommands.{ChangeOrder, ChangeOrderAck, ChangeParent, ChangeParentAck, Create, CreateAck, Remove, RemoveAck}
import im.actor.server.GroupPreQueries.{GetGroupsPre, GetGroupsPreResponse}
import im.actor.server.dialog.UserAcl

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by 98379720172 on 08/02/17.
  */

trait GroupPreOperations extends Commands with Queries

private[grouppre] sealed trait Commands extends UserAcl{

  val processorRegion: GroupPreProcessorRegion

  implicit val timeout:Timeout
  implicit val ec: ExecutionContext

  def create(groupId: Int, userId: Int, authId: Long) : Future[CreateAck] =
    (processorRegion.ref ? Create(groupId=groupId, userId = userId, authId=authId)).mapTo[CreateAck]

  def remove(groupId: Int, userId: Int, authId: Long) : Future[RemoveAck] =
    (processorRegion.ref ? Remove(groupId=groupId, userId = userId, authId=authId)).mapTo[RemoveAck]

  def changeParent(groupId: Int, parentId: Int, userId: Int, authId: Long) : Future[ChangeParentAck] =
    (processorRegion.ref ? ChangeParent(groupId=groupId, parentId=parentId, userId = userId, authId=authId)).mapTo[ChangeParentAck]

  def changeOrder(fromGroupId: Int, toGroupId: Int, userId: Int, authId: Long) : Future[ChangeOrderAck] =
    (processorRegion.ref ? ChangeOrder(fromId=fromGroupId, toId=toGroupId, userId = userId, authId=authId)).mapTo[ChangeOrderAck]
}

private[grouppre] sealed trait Queries{

  val viewRegion: GroupPreViewRegion

  implicit val timeout:Timeout
  implicit val ec: ExecutionContext

  def loadGroupsPre(clientUserId: Int, idGrupoPai:Option[Int]): Future[Seq[GroupPre]] =
      (viewRegion.ref ? GetGroupsPre(groupFatherId = idGrupoPai.getOrElse(0))).mapTo[GetGroupsPreResponse].map(_.groups)

}