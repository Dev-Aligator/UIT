import cv2
import numpy as np
from tkinter import Tk, filedialog
import sys

class FacialRecognitionApp:
    def __init__(self):
        self.face_classifier = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
        if self.face_classifier.empty():
            print("Error: Couldn't load face cascade classifier.")
            sys.exit(1)

    def process_image(self, image_path):
        image = cv2.imread(image_path)
        if image is None:
            print("Error: Couldn't read the image.")
            return

        faces = self.detect_faces(image)
        self.draw_face_boxes(image, faces)
        self.display_image("Facial Recognition Result", image)

    def process_video_stream(self):
        video_capture = cv2.VideoCapture(0)
        if not video_capture.isOpened():
            print("Error: Couldn't open video stream.")
            return

        window_name = "Live Facial Recognition"
        cv2.namedWindow(window_name)

        while True:
            ret, frame = video_capture.read()
            if not ret:
                print("Error: Couldn't read frame.")
                break

            faces = self.detect_faces(frame)
            self.draw_face_boxes(frame, faces)
            cv2.imshow(window_name, frame)

            if self.should_exit(window_name):
                break

        video_capture.release()
        cv2.destroyAllWindows()

    def detect_faces(self, image):
        gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        return self.face_classifier.detectMultiScale(gray_image, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))

    def draw_face_boxes(self, image, faces):
        for (x, y, w, h) in faces:
            cv2.rectangle(image, (x, y), (x+w, y+h), (0, 255, 0), 2)

    def display_image(self, window_name, image):
        cv2.imshow(window_name, image)
        cv2.waitKey(0)
        cv2.destroyAllWindows()

    def should_exit(self, window_name):
        key = cv2.waitKey(1) & 0xFF
        return key == 27 or cv2.getWindowProperty(window_name, cv2.WND_PROP_VISIBLE) < 1

    def select_image(self):
        root = Tk()
        root.withdraw()
        return filedialog.askopenfilename()

def main():
    app = FacialRecognitionApp()

    while True:
        print("\nFacial Recognition Application")
        print("1: Analyze Image")
        print("2: Analyze Video Stream")
        print("3: Exit")
        choice = input("Select an option (1-3): ")

        if choice == '1':
            image_path = app.select_image()
            if image_path:
                app.process_image(image_path)
            else:
                print("No image selected.")
        elif choice == '2':
            app.process_video_stream()
        elif choice == '3':
            print("Exiting application.")
            break
        else:
            print("Invalid option. Please try again.")

if __name__ == "__main__":
    main()
