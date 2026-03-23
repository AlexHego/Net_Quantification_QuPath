/*
August 2024, Qupath version compatible 0.4.1, 0.4.2
Script version 1

BSD 3-Clause License
@author Alexandre Hego
 
 contact: alexandre.hego@uliege.be
 GIGA Cell imaging facility
 University of Liege 

 Goal: 
 This script will measure the MPO postive area, H3-Cit area, the colocalisation and count how many nuclei are in the ROI
  
 /* Set the scale calibration
 ****************************************************/
def server = getCurrentServer()
def cal = server.getPixelCalibration()
double pixelWidth = cal.getPixelWidthMicrons()
double pixelHeight = cal.getPixelHeightMicrons()

/* Detect area of positive MPO, pos H3Cit and intersection
****************************************************/
selectAnnotations();

createAnnotationsFromPixelClassifier("TripleThreshold", 0.0, 0.0)
