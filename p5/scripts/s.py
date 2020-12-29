#Receiver
import time
while node.loop():
	n = node.bufferSize()
	if n>0:
		x = node.read()
		if x=="1":
			node.mark()
		if x=="0":
			node.unmark()
	time.sleep(0.01)