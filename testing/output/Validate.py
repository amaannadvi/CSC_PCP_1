from PIL import Image
import sys

def compare_images(image1_path, image2_path):
    # Open the images
    image1 = Image.open(image1_path)
    image2 = Image.open(image2_path)

    # Check if the sizes are the same
    if image1.size != image2.size:
        return False

    # Compare pixel by pixel
    for x in range(image1.width):
        for y in range(image1.height):
            if image1.getpixel((x, y)) != image2.getpixel((x, y)):
                return False

    return True

# Paths to the images
image1_path = 'output/soutput.png'
image2_path = 'output/poutput.png'

# Compare the images
if compare_images(image1_path, image2_path):
    print("The images are exactly the same.")
else:
    print("The images are different.")
