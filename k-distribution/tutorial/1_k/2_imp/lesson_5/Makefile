KOMPILE_FLAGS+=--gen-bison-parser -ccopt -g
MAKEFILE_PATH := $(dir $(abspath $(lastword $(MAKEFILE_LIST))))
DEF=imp
EXT=imp

-include $(MAKEFILE_PATH)/../../../find-k.mak
include ${K_HOME}/include/kframework/ktest.mak

KRUN_OR_KX=./imp
