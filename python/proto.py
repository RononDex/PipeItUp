
import time
from rpi_ws281x import PixelStrip, Color
from gpiozero import Buzzer
import RPi.GPIO as GPIO # Import Raspberry Pi GPIO library
import argparse

# LED strip configuration:
LED_COUNT = 8        # Number of LED pixels.
LED_PIN = 18          # GPIO pin connected to the pixels (18 uses PWM!).
LED_FREQ_HZ = 800000  # LED signal frequency in hertz (usually 800khz)
LED_DMA = 10          # DMA channel to use for generating signal (try 10)
LED_BRIGHTNESS = 10  # Set to 0 for darkest and 255 for brightest
LED_INVERT = False    # True to invert the signal (when using NPN transistor level shift)
LED_CHANNEL = 0       # set to '1' for GPIOs 13, 19, 41, 45 or 53

strip1 = PixelStrip(LED_COUNT, LED_PIN, LED_FREQ_HZ, LED_DMA, LED_INVERT, LED_BRIGHTNESS, LED_CHANNEL)
strip2 = PixelStrip(1, 13, LED_FREQ_HZ, LED_DMA, LED_INVERT, 255, 1)

def button_callback(channel):
    print("Button was pushed!")
    # Process arguments
    parser = argparse.ArgumentParser()
    parser.add_argument('-c', '--clear', action='store_true', help='clear the display on exit')
    args = parser.parse_args()

    # Create NeoPixel object with appropriate configuration.

    # Intialize the library (must be called once before other functions).
    strip1.begin()
    strip1.setPixelColor(0, Color(255,255,0))
    strip1.setPixelColor(1, Color(255,255,0))
    strip1.setPixelColor(2, Color(255,255,0))
    strip1.setPixelColor(3, Color(255,255,0))
    strip1.setPixelColor(4, Color(255,255,0))
    strip1.setPixelColor(5, Color(255,255,0))
    strip1.setPixelColor(6, Color(255,255,0))
    strip1.setPixelColor(7, Color(255,255,0))
    strip1.show()


def colorWipe(strip, color, wait_ms=50):
    """Wipe color across display a pixel at a time."""
    for i in range(strip.numPixels()):
        strip.setPixelColor(i, color)
        strip.show()
        time.sleep(wait_ms / 1000.0)




# Main program logic follows:
if __name__ == '__main__':

    # Process arguments
    parser = argparse.ArgumentParser()
    parser.add_argument('-c', '--clear', action='store_true', help='clear the display on exit')
    args = parser.parse_args()

    strip2.begin()
    strip2.setPixelColor(0, Color(255,0,0))
    strip2.show()

    GPIO.setwarnings(False) # Ignore warning for now
    GPIO.setmode(GPIO.BOARD) # Use physical pin numbering
    GPIO.setup(15, GPIO.IN, pull_up_down=GPIO.PUD_DOWN) # Set pin 10 to be an input pin and set initial value to be pulled low (off)
    GPIO.add_event_detect(15,GPIO.RISING,callback=button_callback,bouncetime=500) # Setup event on pin 10 rising edge


    message = input("Press enter to quit\n\n") # Run until someone presses enter
    GPIO.cleanup() # Clean up
    colorWipe(strip1, Color(0, 0, 0), 10)
    colorWipe(strip2, Color(0, 0, 0), 10)
