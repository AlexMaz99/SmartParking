#Transmitter
print(dir(node))
import time
print(node.getName())
while node.loop():
	node.send("5")
	time.sleep(1)
	print(node.getInfos())