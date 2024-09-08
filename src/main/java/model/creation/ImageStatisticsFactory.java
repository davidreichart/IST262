package model.creation;

import model.files.IImageFile;
import model.files.ImageStatistics;
import model.service.ImageInspectionService;

public class ImageStatisticsFactory {

    public static ImageStatistics generateStatistics(IImageFile imageFile) {
        ImageInspectionService inspectionService = new ImageInspectionService(imageFile.getFile());
        int[] resolution = inspectionService.getResolution();

        return new ImageStatistics(resolution[0], resolution[1]);
    }
}