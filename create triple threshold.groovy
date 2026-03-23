import qupath.lib.images.servers.PixelCalibration
import qupath.opencv.ml.pixel.PixelClassifiers
import qupath.opencv.ops.ImageOps
import java.nio.file.Path
import static qupath.lib.gui.scripting.QPEx.*

String pixelClassifierFolder="D:/Data/Pierre/Pierre_PathoAout2/classifiers/" //project subfolder with pixel classifier
String newClassifierName="TripleThreshold.json" //new classifier name. Must have .json ending.
String newPathClass = 'Coloc'
double scale = 2.0 //0.5x scale from original image

//define preprocessing and thresholds
def ops2 = [
        ImageOps.Channels.extract(0,1,2), //choose the channels for thresholding (0-based)
        ImageOps.Filters.gaussianBlur(2), //1x gaussian smoothing
        ImageOps.Threshold.threshold(2500,2000,2500), //threshold for each channel, matching the order of the extraction
        ImageOps.Channels.minimum() //minimum value for finding double positive regions
        //ImageOps.Channels.maximum() //maximum value for finding union regions
]
def op = ImageOps.buildImageDataOp().appendOps(*ops2)

//get pixel calibration
imageData = getCurrentImageData()
PixelCalibration cal=imageData.getServer().getPixelCalibration()

Map classmap=Map.of(1,getPathClass(newPathClass)) //define intensity 1 = newPathClass

newclassifier=PixelClassifiers.createClassifier(op,cal.createScaledInstance(scale,scale),classmap) //create the classifier

//write new classifier to file
Path writepath=Path.of(pixelClassifierFolder,newClassifierName)
PixelClassifiers.writeClassifier(newclassifier,writepath)