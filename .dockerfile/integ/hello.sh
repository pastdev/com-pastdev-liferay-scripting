#!/bin/bash

curl http://localhost:8080/api/jsonws/pastdev_scripting.scriptingexecutor/eval \
    -u admin@liferay.com:set4now \
    -d language='groovy' \
    -d script='print("hello, world");'
