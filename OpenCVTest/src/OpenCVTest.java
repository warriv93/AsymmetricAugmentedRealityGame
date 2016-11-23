import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class OpenCVTest {
	
	static CvScalar rgba_min = cvScalar(0, 0, 0, 0);// RED wide dabur birko
    static CvScalar rgba_max = cvScalar(255, 255, 255, 255);

	public static void main(String[] args) {	

		
		FFmpegFrameGrabber grabber =new FFmpegFrameGrabber("http://root:pass@192.168.20.253/axis-cgi/mjpg/video.cgi?resolution=640x480&fps=25");
		
		try {
			grabber.start();
			OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
			IplImage grabbedImage = converter.convert(grabber.grab());
			
			CanvasFrame frameOriginal = new CanvasFrame("Original", CanvasFrame.getDefaultGamma()/grabber.getGamma());
			CanvasFrame frameProcessed = new CanvasFrame("Processed", CanvasFrame.getDefaultGamma()/grabber.getGamma());
			
			while (frameOriginal.isVisible() && (grabbedImage = converter.convert(grabber.grab())) != null) {
				
				IplImage imgThreshold = cvCreateImage(cvGetSize(grabbedImage), 8, 1);
		        //
		        cvInRangeS(grabbedImage, rgba_min, rgba_max, imgThreshold);// red
		        //cvSmooth(imgThreshold, imgThreshold, CV_MEDIAN, 15,0,0,0);
		        
		        Frame fP = converter.convert(imgThreshold);
				frameProcessed.showImage(fP);
				
				/*Mat imgHSV;

				cvtColor(imgOriginal, imgHSV, COLOR_BGR2HSV); //Convert the captured frame from BGR to HSV

				Mat imgThresholded;

				inRange(imgHSV, Scalar(iLowH, iLowS, iLowV), Scalar(iHighH, iHighS, iHighV), imgThresholded); //Threshold the image

				//morphological opening (removes small objects from the foreground)
				erode(imgThresholded, imgThresholded, getStructuringElement(MORPH_ELLIPSE, Size(5, 5)));
				dilate(imgThresholded, imgThresholded, getStructuringElement(MORPH_ELLIPSE, Size(5, 5)));

				//morphological closing (removes small holes from the foreground)
				dilate(imgThresholded, imgThresholded, getStructuringElement(MORPH_ELLIPSE, Size(5, 5)));
				erode(imgThresholded, imgThresholded, getStructuringElement(MORPH_ELLIPSE, Size(5, 5)));

				//Calculate the moments of the thresholded image
				Moments oMoments = moments(imgThresholded);

				double dM01 = oMoments.m01;
				double dM10 = oMoments.m10;
				double dArea = oMoments.m00;

				// if the area <= 10000, I consider that the there are no object in the image and it's because of the noise, the area is not zero 
				if (dArea > 10000)
				{
					//calculate the position of the ball
					int posX = dM10 / dArea;
					int posY = dM01 / dArea;

					if (iLastX >= 0 && iLastY >= 0 && posX >= 0 && posY >= 0)
					{
						//Draw a red line from the previous point to the current point
						line(imgLines, Point(posX, posY), Point(iLastX, iLastY), Scalar(0, 0, 255), 2);
					}

					iLastX = posX;
					iLastY = posY;
				}*/
				
				Frame f = converter.convert(grabbedImage);
				frameOriginal.showImage(f);
			
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//         Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
//         System.out.println("mat = " + mat.dump());
//
//         
//         VideoCapture cap = new VideoCapture("http://root:pass@192.168.20.253/axis-cgi/mjpg/video.cgi");
//         
//         if(cap.isOpened()){
//        	 System.out.println("It worked!");
//         }
	}

}