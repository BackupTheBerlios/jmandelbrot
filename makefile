jmandelbrot.jar: Benoit.java DrawPanel.java
	mkdir bin
	javac -d bin Benoit.java
	cd ./bin; echo "Main-Class: Benoit" > manifest.txt; jar -cvfm jmandelbrot.jar manifest.txt *.class; chmod +x jmandelbrot.jar
	echo "Cleaning up..."
	rm ./bin/*.class ./bin/manifest.txt
