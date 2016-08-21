
# capture builtins

VARS_OLD	:= $(.VARIABLES)

# external tools

ANT		:= ant
PROTOC	:= protoc
JAVAFY	:= ./scripts/javafy.sh

# base messages

MESSAGE_DIR 		:= src/messages
MESSAGE_NAMESPACE	:= dndapp/proto/messages
MESSAGE_SOURCES 	:= $(wildcard $(MESSAGE_DIR)/*)

# java outputs

JAVA_DEST		:= src
JAVA_TARGETS	:= $(addprefix $(JAVA_DEST)/$(MESSAGE_NAMESPACE)/, \
						$(shell $(JAVAFY) $(notdir $(MESSAGE_SOURCES:.proto=.java))))

# rules

all: java-project

.PHONY: all clean vars variables \
		java-protobuf java-protobuf-clean java-protobuf-clean-all \
		java-project java-project-clean

vars: variables
variables:
	$(foreach v, \
		$(sort $(filter-out $(VARS_OLD) VARS_OLD,$(.VARIABLES))), \
		$(info $(v) := $($(v))))

clean: java-protobuf-clean java-project-clean

# Java protobuf

java-protobuf: $(JAVA_TARGETS)

java-protobuf-clean:
	@rm -fv $(JAVA_TARGETS)

java-protobuf-clean-all:
	@rm -fv $(JAVA_DEST)/$(MESSAGE_NAMESPACE)/*

# match each *.java file to its corresponding *.proto file
$(eval $(join $(addsuffix :,$(JAVA_TARGETS)), $(MESSAGE_SOURCES)))

%.java:
	$(PROTOC) -I=$(MESSAGE_DIR) --java_out=$(JAVA_DEST) $^

# Java project

java-project: java-protobuf
	$(ANT)

java-project-clean:
	$(ANT) clean
