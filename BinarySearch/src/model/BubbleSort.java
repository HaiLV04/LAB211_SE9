// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package model;
// 
// /**
//  * Chức năng: Sắp xếp mảng số nguyên bằng thuật toán Bubble Sort.
//  * Luồng tương tác:
//  * - Nhận đầu vào là một mảng và sắp xếp các phần tử của mảng đó theo thứ tự tăng dần.
//  *
//  * @author Tuan Tran
//  */
// public class BubbleSort {
// 
//     /**
//      * Chức năng: Sắp xếp mảng theo thứ tự tăng dần bằng thuật toán Bubble Sort.
//      * Luồng xử lý:
//      * 1. Kiểm tra mảng đầu vào null hoặc chỉ có 1 phần tử thì kết thúc luôn.
//      * 2. Sử dụng vòng lặp ngoài duyệt từ đầu đến cuối mảng để đẩy phần tử lớn nhất về cuối.
//      * 3. Sử dụng vòng lặp trong so sánh 2 phần tử liền kề và hoán đổi vị trí nếu phần tử trước lớn hơn phần tử sau.
//      *
//      * @param array mảng cần sắp xếp
//      */
//     public static void sort(int[] array) {
//         if (array == null || array.length <= 1) return;
//         int n = array.length;
//         for (int i = 0; i < n - 1; i++) {
//             for (int j = 0; j < n - i - 1; j++) {
//                 if (array[j] > array[j + 1]) {
//                     int temp = array[j];
//                     array[j] = array[j + 1];
//                     array[j + 1] = temp;
//                 }
//             }
//         }
//     }
// }
