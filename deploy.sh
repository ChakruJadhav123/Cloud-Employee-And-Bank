#!/bin/bash
echo "Building project..."
mvn clean package
echo "Running application..."
java -jar target/*-jar-with-dependencies.jar
