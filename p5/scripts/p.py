#Transmitter
import time
print(node.getName())
while node.loop():
	node.send("5")
	time.sleep(1)