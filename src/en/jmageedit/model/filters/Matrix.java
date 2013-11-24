package en.jmageedit.model.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix extends Filter {
    private static final int GREEN_RGB = Color.GREEN.getRGB();
    private static final int BLACK_RGB = Color.BLACK.getRGB();
    private static final int[] NONE = {BLACK_RGB, BLACK_RGB, BLACK_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, BLACK_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, BLACK_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, BLACK_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, BLACK_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, BLACK_RGB, BLACK_RGB};
    private static final int[] ZERO = {GREEN_RGB, GREEN_RGB, GREEN_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, BLACK_RGB, BLACK_RGB, GREEN_RGB,
                                       GREEN_RGB, GREEN_RGB, GREEN_RGB, GREEN_RGB}; // 4 x 6
    private static final int[] ONE  = {BLACK_RGB, GREEN_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB,
                                       BLACK_RGB, BLACK_RGB, GREEN_RGB, BLACK_RGB}; // 4 x 6

    @Override
    public BufferedImage filter(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        
        BufferedImage outImage = new BufferedImage(w, h, img.getType());
        
        float[][] megaPixels = new float[w / 5][h / 7];
        float maxAverage = 0.0f;
        for(int x=0; x<w-4; x+=5) {
            for(int y=0; y<h-6; y+=7) {
                int[] sourcePixels = img.getRGB(x, y, 4, 6, new int[20], 0, 1);
                float sum = 0.0f;
                for(int i=0; i<sourcePixels.length; i++) {
                    Color c = new Color(sourcePixels[i]);
                    int average = (c.getRed() + c.getGreen() + c.getBlue()) / 3; // smaller # = darker 
                    float averageF = ((float) average) / 255.0f;
                    sum += averageF;
                }
                float finalAverage = sum / ((float) sourcePixels.length);
                megaPixels[x/5][y/7] = finalAverage;
                if(finalAverage > maxAverage) {
                    maxAverage = finalAverage;
                }
            }
        }
        
        //float zeroThreshold = maxAverage * 0.66f;
        //float oneThreshold = maxAverage * 0.33f;
        List<Float> allMegaPixels = new ArrayList<Float>();
        for(int i=0; i<megaPixels.length; i++) {
            for(int j=0; j<megaPixels[i].length; j++) {
                if(!allMegaPixels.contains(megaPixels[i][j])) {
                    allMegaPixels.add(megaPixels[i][j]);
                }
            }
        }
        Collections.sort(allMegaPixels);
        int len = allMegaPixels.size();
        for(int i=0; i<len; i++) {
            System.out.println(allMegaPixels.get(i));
        }
        float zeroThreshold = allMegaPixels.get((int) (len * 0.66));
        float oneThreshold = allMegaPixels.get((int) (len * 0.33));
        //System.out.printf("0 = %f at %d%n", zeroThreshold, (int) (len * 0.66));
        //System.out.printf("0 = %f at %d%n", oneThreshold, (int) (len * 0.33));
        
        for(int x=0; x<megaPixels.length; x++) {
            for(int y=0; y<megaPixels[x].length; y++) {
                if(megaPixels[x][y] > zeroThreshold) {
                    outImage.setRGB(x * 5, y * 7, 4, 6, ZERO, 0, 4);
                } else if(megaPixels[x][y] > oneThreshold) {
                    outImage.setRGB(x * 5, y * 7, 4, 6, ONE, 0, 4);
                } else {
                    outImage.setRGB(x * 5, y * 7, 4, 6, NONE, 0, 4);
                }
            }
        }
        
        return outImage;
    }
}
