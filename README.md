# Net_Quantification_QuPath

## Paper

## Description

This repository contains a QuPath-based workflow for **NETs quantification** from fluorescence images using two Groovy scripts.

Briefly, a pixel classifier is generated in QuPath using a **triple-threshold strategy** applied to three fluorescence channels (e.g. DAPI, Cit-H3 and MPO). The script [create triple threshold.groovy](https://github.com/AlexHego/Net_Quantification_QuPath/blob/main/create%20triple%20threshold.groovy) builds this classifier by applying a Gaussian blur to each channel, followed by fixed intensity thresholds. The processed channels are then combined to detect the **colocalized signal**, corresponding to NETs.

The second script, [Analyse_Nets_hego.groovy](https://github.com/AlexHego/Net_Quantification_QuPath/blob/main/Analyse_Nets_hego.groovy), applies this classifier to user-defined regions of interest (ROIs). It generates detections corresponding to NETs-positive areas and enables the quantification of relevant features such as marker-positive areas, colocalization, and nuclei count within each ROI.

Finally, measurements can be exported directly from QuPath as a **.csv (comma-separated values)** file for downstream analysis. Normalization steps, such as dividing the colocalized area by the number of cells, can be performed after export if required.


## Step-by-step tutorial

1. Download the two Groovy scripts:
   - `create triple threshold.groovy`
   - `Analyse_Nets_hego.groovy`

2. Open your project in **QuPath**

3. Import your fluorescence images

4. Draw or select the **annotations (ROIs)** to analyze

5. Run the classifier script:
   - `Automate > Show script editor`
   - Open `create triple threshold.groovy`
   - Click **Run**
   - This will create the `TripleThreshold` pixel classifier

6. Run the analysis script:
   - Open `Analyse_Nets_hego.groovy`
   - Click **Run**
   - This will apply the classifier and perform measurements

7. Export results:
   - `Measure > Show measurement table`
   - `Export measurements` → save as `.csv`

## Notes

Threshold values are currently **fixed in the script** and may require adjustment depending on imaging conditions. In our case, thresholds were validated by measuring the mean intensity plus two standard deviations (mean + 2 SD) from autofluorescence or negative control samples.

The channel order must match the acquisition setup used during imaging.
