LOCAL_PATH := $(call my-dir)

LOCAL_MODULE    := avutil

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_SRC_FILES := ./ffmpeg/armv7-a/libavutil.a
else
    ifeq ($(TARGET_ARCH_ABI),armeabi)
        LOCAL_SRC_FILES := ./ffmpeg/armv5te/libavutil.a
    else
        ifeq ($(TARGET_ARCH_ABI),x86)
            LOCAL_SRC_FILES := ./ffmpeg/i686/libavutil.a
        endif
    endif
endif

include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := avformat

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_SRC_FILES := ./ffmpeg/armv7-a/libavformat.a
else
    ifeq ($(TARGET_ARCH_ABI),armeabi)
    LOCAL_SRC_FILES := ./ffmpeg/armv5te/libavformat.a
    else
        ifeq ($(TARGET_ARCH_ABI),x86)
        LOCAL_SRC_FILES := ./ffmpeg/i686/libavformat.a
        endif
    endif
endif

include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := avcodec

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_SRC_FILES := ./ffmpeg/armv7-a/libavcodec.a
else
    ifeq ($(TARGET_ARCH_ABI),armeabi)
    LOCAL_SRC_FILES := ./ffmpeg/armv5te/libavcodec.a
    else
        ifeq ($(TARGET_ARCH_ABI),x86)
        LOCAL_SRC_FILES := ./ffmpeg/i686/libavcodec.a
        endif
    endif
endif

include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := crypto

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_SRC_FILES := ./boringssl/lib/libcrypto_armeabi-v7a.a
else
    ifeq ($(TARGET_ARCH_ABI),armeabi)
	LOCAL_SRC_FILES := ./boringssl/lib/libcrypto_armeabi.a
    else
        ifeq ($(TARGET_ARCH_ABI),x86)
	    LOCAL_SRC_FILES := ./boringssl/lib/libcrypto_x86.a
        endif
    endif
endif

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_CFLAGS := -Wall -DANDROID -DHAVE_MALLOC_H -DHAVE_PTHREAD -DWEBP_USE_THREAD -finline-functions -ffast-math -ffunction-sections -fdata-sections -O0
LOCAL_C_INCLUDES += ./jni/libwebp/src
LOCAL_ARM_MODE := arm
LOCAL_STATIC_LIBRARIES := cpufeatures
LOCAL_MODULE := webp

ifneq ($(findstring armeabi-v7a, $(TARGET_ARCH_ABI)),)
  NEON := c.neon
else
  NEON := c
endif

LOCAL_SRC_FILES := \
./libwebp/dec/alpha.c \
./libwebp/dec/buffer.c \
./libwebp/dec/frame.c \
./libwebp/dec/idec.c \
./libwebp/dec/io.c \
./libwebp/dec/quant.c \
./libwebp/dec/tree.c \
./libwebp/dec/vp8.c \
./libwebp/dec/vp8l.c \
./libwebp/dec/webp.c \
./libwebp/dsp/alpha_processing.c \
./libwebp/dsp/alpha_processing_sse2.c \
./libwebp/dsp/cpu.c \
./libwebp/dsp/dec.c \
./libwebp/dsp/dec_clip_tables.c \
./libwebp/dsp/dec_mips32.c \
./libwebp/dsp/dec_neon.$(NEON) \
./libwebp/dsp/dec_sse2.c \
./libwebp/dsp/enc.c \
./libwebp/dsp/enc_avx2.c \
./libwebp/dsp/enc_mips32.c \
./libwebp/dsp/enc_neon.$(NEON) \
./libwebp/dsp/enc_sse2.c \
./libwebp/dsp/lossless.c \
./libwebp/dsp/lossless_mips32.c \
./libwebp/dsp/lossless_neon.$(NEON) \
./libwebp/dsp/lossless_sse2.c \
./libwebp/dsp/upsampling.c \
./libwebp/dsp/upsampling_neon.$(NEON) \
./libwebp/dsp/upsampling_sse2.c \
./libwebp/dsp/yuv.c \
./libwebp/dsp/yuv_mips32.c \
./libwebp/dsp/yuv_sse2.c \
./libwebp/enc/alpha.c \
./libwebp/enc/analysis.c \
./libwebp/enc/backward_references.c \
./libwebp/enc/config.c \
./libwebp/enc/cost.c \
./libwebp/enc/filter.c \
./libwebp/enc/frame.c \
./libwebp/enc/histogram.c \
./libwebp/enc/iterator.c \
./libwebp/enc/picture.c \
./libwebp/enc/picture_csp.c \
./libwebp/enc/picture_psnr.c \
./libwebp/enc/picture_rescale.c \
./libwebp/enc/picture_tools.c \
./libwebp/enc/quant.c \
./libwebp/enc/syntax.c \
./libwebp/enc/token.c \
./libwebp/enc/tree.c \
./libwebp/enc/vp8l.c \
./libwebp/enc/webpenc.c \
./libwebp/utils/bit_reader.c \
./libwebp/utils/bit_writer.c \
./libwebp/utils/color_cache.c \
./libwebp/utils/filters.c \
./libwebp/utils/huffman.c \
./libwebp/utils/huffman_encode.c \
./libwebp/utils/quant_levels.c \
./libwebp/utils/quant_levels_dec.c \
./libwebp/utils/random.c \
./libwebp/utils/rescaler.c \
./libwebp/utils/thread.c \
./libwebp/utils/utils.c \

include $(BUILD_STATIC_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := actorsdk.24
LOCAL_CFLAGS    := -w -std=c11 -Os -DNULL=0 -DSOCKLEN_T=socklen_t -DLOCALE_NOT_USED -D_LARGEFILE_SOURCE=1 -D_FILE_OFFSET_BITS=64
LOCAL_CFLAGS    += -Drestrict='' -D__EMX__ -DOPUS_BUILD -DFIXED_POINT -DUSE_ALLOCA -DHAVE_LRINT -DHAVE_LRINTF -fno-math-errno
LOCAL_CFLAGS    += -DANDROID_NDK -DDISABLE_IMPORTGL -fno-strict-aliasing -fprefetch-loop-arrays -DAVOID_TABLES -DANDROID_TILE_BASED_DECODE -DANDROID_ARMV6_IDCT -ffast-math -D__STDC_CONSTANT_MACROS
LOCAL_CPPFLAGS  := -DBSD=1 -ffast-math -Os -funroll-loops -std=c++11
LOCAL_LDLIBS    := -ljnigraphics -llog -lz -latomic
LOCAL_STATIC_LIBRARIES := webp avformat avcodec avutil

LOCAL_C_INCLUDES    := \
./jni/libyuv/include \
./jni/boringssl/include \
./jni/ffmpeg/include

LOCAL_SRC_FILES     += \
./libyuv/source/compare_common.cc \
./libyuv/source/compare_gcc.cc \
./libyuv/source/compare_neon64.cc \
./libyuv/source/compare_win.cc \
./libyuv/source/compare.cc \
./libyuv/source/convert_argb.cc \
./libyuv/source/convert_from_argb.cc \
./libyuv/source/convert_from.cc \
./libyuv/source/convert_jpeg.cc \
./libyuv/source/convert_to_argb.cc \
./libyuv/source/convert_to_i420.cc \
./libyuv/source/convert.cc \
./libyuv/source/cpu_id.cc \
./libyuv/source/mjpeg_decoder.cc \
./libyuv/source/mjpeg_validate.cc \
./libyuv/source/planar_functions.cc \
./libyuv/source/rotate_any.cc \
./libyuv/source/rotate_argb.cc \
./libyuv/source/rotate_common.cc \
./libyuv/source/rotate_gcc.cc \
./libyuv/source/rotate_mips.cc \
./libyuv/source/rotate_neon64.cc \
./libyuv/source/rotate_win.cc \
./libyuv/source/rotate.cc \
./libyuv/source/row_any.cc \
./libyuv/source/row_common.cc \
./libyuv/source/row_gcc.cc \
./libyuv/source/row_mips.cc \
./libyuv/source/row_neon64.cc \
./libyuv/source/row_win.cc \
./libyuv/source/scale_any.cc \
./libyuv/source/scale_argb.cc \
./libyuv/source/scale_common.cc \
./libyuv/source/scale_gcc.cc \
./libyuv/source/scale_mips.cc \
./libyuv/source/scale_neon64.cc \
./libyuv/source/scale_win.cc \
./libyuv/source/scale.cc \
./libyuv/source/video_common.cc

ifeq ($(TARGET_ARCH_ABI),armeabi-v7a)
    LOCAL_CFLAGS += -DLIBYUV_NEON
    LOCAL_SRC_FILES += \
        ./libyuv/source/compare_neon.cc.neon    \
        ./libyuv/source/rotate_neon.cc.neon     \
        ./libyuv/source/row_neon.cc.neon        \
        ./libyuv/source/scale_neon.cc.neon
endif

LOCAL_SRC_FILES     += \
./jni.c \
./utils.c \
./image.c \
./video.c \
./gifvideo.cpp \
./NativeLoader.cpp

include $(BUILD_SHARED_LIBRARY)

$(call import-module,android/cpufeatures)