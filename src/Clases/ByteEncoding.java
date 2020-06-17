/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author fs
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ByteEncoding {

    private static int colores = 16;
    private static int width = 1310;
    private static int height = 1700;
    private static int bufferLength = 8;
    private int sequenceLength;
    private static String outputfilepath = "output.bin";
    
    
	private static List<Byte> encodeSequence(Vector<Integer> sequence) {
		List<Byte> result = new ArrayList<Byte>();

		byte buffer = 0;
		int bufferPos = 0;

		int i = 0;
		while (i < sequence.length) {
			// La operación de corrimiento pone un '0'
			buffer = (byte) (buffer << 1);
			bufferPos++;
			if (sequence.get(i) == '1') {
				buffer = (byte) (buffer | 1);
			}

			if (bufferPos == bufferLength) {
				result.add(buffer);
				buffer = 0;
				bufferPos = 0;
			}

			i++;
		}

		if ((bufferPos < bufferLength) && (bufferPos != 0)) {
			buffer = (byte) (buffer << (bufferLength - bufferPos));
			result.add(buffer);
		}

		return result;
	}

	private static char[] decodeSequence() {
		char[] restoredSequence = new char[sequenceLength];
		
		try {
			byte[] inputSequence = Files.readAllBytes(new File(outputfilepath).toPath());			
			int globalIndex = 0;			
			byte mask = (byte) (1 << (bufferLength - 1)); // mask: 10000000
			int bufferPos = 0;
			int i = 0;
			while (globalIndex < sequenceLength) 
			{
				byte buffer = inputSequence[i];			
				while (bufferPos < bufferLength) {
					
					if ((buffer & mask) == mask) {
						restoredSequence[globalIndex] = '1';
					} else {
						restoredSequence[globalIndex] = '0';
					}
					
					buffer = (byte) (buffer << 1);
					bufferPos++;
					globalIndex++;
					
					if (globalIndex == sequenceLength) {
						break;
					}
				}
				i++;
				bufferPos = 0;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return restoredSequence;
	}
        
         

	public  void encondeDecodeHuffMann(Imagen img, Vector<Codificacion> codificacion) {
                            		
		Vector <Integer> secuenciaOriginal = img.getSecuencia();
                                    convertirABinario (secuenciaOriginal, codificacion);
                                    
                                    
                        
                                  
                                    

		try {
			List<Byte> encodedSequence = encodeSequence(originalSequence);
			byte[] byteArray = ConvertByteListToPrimitives(encodedSequence);

			FileOutputStream fos = new FileOutputStream(outputfilepath);
			fos.write(byteArray);
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// --------------------------
		
		System.out.println("* Sequencia recuperada desde archivo:");
		printSequence(decodeSequence());
	}
	
	public static void printSequence(char[] sequence) {
		for (int i = 0; i < sequence.length; i++) {
			System.out.print(sequence[i]);
		}
	}
        
                

	private static byte[] ConvertByteListToPrimitives(List<Byte> input) {
		byte[] ret = new byte[input.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = input.get(i);
		}

		return ret;
	}

	
}