#Receiver
import time	
flag = 0
while node.loop():
	n = node.bufferSize()

	if n>0:
		x = node.read()
		if (flag == 0):
			i = "cupcarbon/take/1/"+node.getName()
			node.publish(i, "3")
		node.mark()
		flag = 1
	elif (flag == 1):
		j = "cupcarbon/leave/1/"+node.getName()
		node.publish(j, "3")
		node.unmark()
		flag = 0
	time.sleep(1)