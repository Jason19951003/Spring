package security.hash;

import security.KeyUtil;

// 針對mysalary.txt 產生hash
public class SalaryHashGenerator {
	public static void main(String[] args) {
		String path = "src/security/hash/my_salary.txt";
		// 取得Hash
		String fileHash = KeyUtil.generateFileHash(path);
		// 印出Hash
		if (fileHash == null) {
			System.out.println("Error");
			return;
		}
		System.out.printf("%s 的 SHA-256 Hash: %s%n", path, fileHash);
	}
}
