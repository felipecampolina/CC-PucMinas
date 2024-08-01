import socket
import random
import time
import threading


def tcp_server():
    server_address = ("0.0.0.0", 9999)
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(server_address)
    server_socket.listen(1)

    print("TCP server up and listening")

    def handle_client(client_socket):
        try:
            while True:
                response = str(random.uniform(0, 10))
                client_socket.sendall((response + "\n").encode())
                print(f"Sent: {response}")
                time.sleep(1)
        except (ConnectionResetError, BrokenPipeError):
            print("Connection closed by client")
        finally:
            client_socket.close()

    while True:
        client_socket, client_address = server_socket.accept()
        print(f"Connection from {client_address}")
        client_thread = threading.Thread(target=handle_client, args=(client_socket,))
        client_thread.start()


def udp_server():
    udp_server_address = ("0.0.0.0", 9998)
    udp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    udp_server_socket.bind(udp_server_address)

    print("UDP server up and listening")

    while True:
        data, client_address = udp_server_socket.recvfrom(256)
        if data:
            message = data.decode().strip()
            print(f"Received: {message} from {client_address}")
            action, price = message.split()
            price = float(price)
            # In a real scenario, you might have more logic to check the transaction validity
            if action in ["buy", "sell"]:
                udp_server_socket.sendto(b"success", client_address)
                print(f"Transaction {action} at price {price} confirmed.")
            else:
                udp_server_socket.sendto(b"failure", client_address)


if __name__ == "__main__":
    threading.Thread(target=tcp_server).start()
    threading.Thread(target=udp_server).start()
