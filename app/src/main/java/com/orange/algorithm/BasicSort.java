package com.orange.algorithm;

/**
 * created by czh on 2019/4/27
 */
public class BasicSort {

    //bubble sort
    void doBubbleSort(int[] src) {
        int temp;
        for (int i = 0; i < src.length; i++) {
            for (int j = i+1; j < src.length; j++) {
                if (src[i]>src[j]){
                    temp=src[i];
                    src[i]=src[j];
                    src[j]=temp;
                }
            }
        }
    }

    //select sort
    void doChooseSort(int[] src) {
        int temp;
        int minIndex;
        for (int i = 0; i < src.length; i++) {
            minIndex=i;
            temp=src[i];
            for (int j = i+1; j < src.length; j++) {
                if (src[i]>temp){
                    minIndex=j;
                    temp=src[j];
                }
            }
            src[minIndex]=src[i];
            src[i]=temp;
        }
    }

    //insert
    void doInsertSort2(int[] src) {
        for (int i = 1; i < src.length; i++) {
            int temp=src[i];
            int j;
            for (j = i; j >0 ; j--) {
                if (src[j-1]>temp){
                    src[j]=src[j-1];
                }else {
                    break;
                }
            }
            src[j]=temp;
        }
    }

    //quict
    public static int[] QuickSort0(int[] pData, int left, int right) {
        int i=left;
        int j=right;
        int midValue=pData[(i+j)/2];
        do {
            while (pData[i]<midValue && i<right){
                i++;
            }
            while (pData[j]>midValue && j>left){
                j--;
            }
            if (i<j){
                int temp=pData[i];
                pData[i]=pData[j];
                pData[j]=temp;
                i++;
                j--;
            }
            if (i==j){
                i++;
                j--;
            }
        }while (i<=j);

        if (left<j){
            QuickSort0(pData,left,j);
        }

        if (right>i){
            QuickSort0(pData,i,right);
        }
        return pData;
    }

    //

    //

}
