from socket import *
serverName = gethostbyname(gethostname())
serverPort = 12000
ADDR = (serverName, serverPort)
clientSocket = socket(AF_INET, SOCK_DGRAM)
message = input('Input lower case: ')
clientSocket.sendto(bytes(message, 'utf8'), ADDR)
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)
print(modifiedMessage.decode())
clientSocket.close()
