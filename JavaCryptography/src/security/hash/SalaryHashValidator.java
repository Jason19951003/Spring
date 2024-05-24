package security.hash;

import security.KeyUtil;

// 驗證 my_salary.txt 內容是否遭受竄改
public class SalaryHashValidator {

	public static void main(String[] args) {
		// 透過 SalaryHashGenerator.java 先得知Hash
		String knowHash = "e424d076e9a2fd7934e5c7307c22564a9ad9f62935d15b11a325cb0f7477a3ca";

		// 重新針對my_salary.txt產生Hash 
		// 比較 knowHash == hash 是否相等
		String filePath = "src/security/hash/my_salary.txt";
		// 取得Hash
		String fileHash = KeyUtil.generateFileHash(filePath);
		
		if (knowHash.equals(fileHash)) {
			System.out.println("文件沒有被修改 " + filePath);
		} else {
			System.out.println("文件被修改過 " + filePath);
		}
		
	}

}
