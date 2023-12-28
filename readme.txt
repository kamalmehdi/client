javac src/*.java -d bin/
jar cvfm game.jar Manifest.txt bin/

java -jar game.jar