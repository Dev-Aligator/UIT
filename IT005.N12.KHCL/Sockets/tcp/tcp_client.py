from socket import *
serverName = gethostbyname(gethostname())
serverPort = 12000

ADDR = (serverName, serverPort)

clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect(ADDR)
sentence = input('input lower case sentence: ')
clientSocket.send(bytes(sentence, 'utf8'))
modifiedSentence = clientSocket.recv(1024)
print('From server: ', modifiedSentence.decode())
clientSocket.close()
