#!/bin/sh

read -p 'Assignment name (ex. assn01): ' name
read -p 'Primary class (ex. HelloWorld): ' class

mkdir ./assignments/src/$name

echo "package $name;\n\npublic class $class {\n\n}" > ./assignments/src/$name/$class.java
