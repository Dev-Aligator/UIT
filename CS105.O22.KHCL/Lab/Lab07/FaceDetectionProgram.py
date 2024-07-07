import cv2
import tkinter as tk
from tkinter import filedialog

def detect_faces_image(image_path):
    # Load the cascade
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
    
    # Read the input image
    img = cv2.imread(image_path)
    
    # Convert into grayscale
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    
    # Detect faces
    faces = face_cascade.detectMultiScale(gray, 1.1, 4)
    
    # Draw rectangle around the faces
    for (x, y, w, h) in faces:
        cv2.rectangle(img, (x, y), (x+w, y+h), (255, 0, 0), 2)
    
    # Display the output
    cv2.imshow('img', img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()

def detect_faces_video():
    # Load the cascade
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
    
    # To capture video from webcam
    cap = cv2.VideoCapture(0)
    
    while True:
        # Read the frame
        _, img = cap.read()
        
        # Convert to grayscale
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        
        # Detect the faces
        faces = face_cascade.detectMultiScale(gray, 1.1, 4)
        
        # Draw the rectangle around each face
        for (x, y, w, h) in faces:
            cv2.rectangle(img, (x, y), (x+w, y+h), (255, 0, 0), 2)
        
        # Display
        cv2.imshow('img', img)
        
        # Stop if escape key is pressed
        k = cv2.waitKey(30) & 0xff
        if k == 27:
            break
    
    # Release the VideoCapture object
    cap.release()
    cv2.destroyAllWindows()

def choose_image():
    root = tk.Tk()
    root.withdraw()
    file_path = filedialog.askopenfilename()
    return file_path

def main():
    print("Choose an option:")
    print("1. Detect faces in an image")
    print("2. Detect faces in video (webcam)")
    
    choice = input("Enter your choice (1 or 2): ")
    
    if choice == '1':
        image_path = choose_image()
        if image_path:
            detect_faces_image(image_path)
        else:
            print("No image selected.")
    elif choice == '2':
        detect_faces_video()
    else:
        print("Invalid choice. Please run the program again and select 1 or 2.")

if __name__ == "__main__":
    main()
