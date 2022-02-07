class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        
        
        String lcs = printLCP(str1, str2);        
        int[] pre1 = new int[str1.length()];
        int[] pre2 = new int[str2.length()];
        fillPre(str1, pre1, lcs);
        fillPre(str2, pre2, lcs);
        int i  = 0, j = 0, k = 0;
        
        StringBuilder sb= new StringBuilder();
        
        while(pre1[i] == 0){
                sb.append(str1.charAt(i));
                i++;
        }
            
        while(pre2[j] == 0){
            sb.append(str2.charAt(j));
            j++;
        }
        
        while(i < str1.length() && j < str2.length()){           
            
            if(pre1[i] == 1 && pre2[j] == 1){
                sb.append(lcs.charAt(k));
                i++;
                j++;
                k++;
            } else if(pre1[i] == 0 && pre2[j] == 0){
                sb.append(str1.charAt(i));
                sb.append(str2.charAt(j));
                i++;
                j++;
            } else if(pre1[i] == 1 && pre2[j] == 0){
                sb.append(str2.charAt(j));
                j++;
            } else if(pre2[j] == 1 && pre1[i] == 0){
                sb.append(str1.charAt(i));
                i++;
            }
            
        }
        
        if(j < str2.length() && i >= str1.length()){
            while(pre2[j] == 0){
                sb.append(str2.charAt(j));
                j++;
                if(j == str2.length())  break;
            }

            
        }

        if(i < str1.length() && j >= str2.length()){
            while(pre1[i] == 0){
                sb.append(str1.charAt(i));
                i++;
                if(i == str1.length()) break;
            }
            
        }
        
        
        
        return sb.toString();
    }
    
    static void fillPre(String s, int[] nums, String lcs){
        
        char[] ch = lcs.toCharArray();
        
        int j  = 0;
        
        for(int i  = 0; i < s.length(); i++){
            
            if(j < ch.length && s.charAt(i) == ch[j]){
                nums[i] = 1;
                j++;
                
            }
            
            
        }
        
        return;
        
    }
    
    static int maxValue(int a, int b)
    {
        if (a > b)
        {
            return a;
        }
        return b;
    }
    public String printLCP(String text1, String text2)
    {
        // Get the length of text1 and text2
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // Execute loop through by size of m
        for (int i = 0; i <= m; i++)
        {
            // Execute loop through by size of n
            for (int j = 0; j <= n; j++)
            {
                if (i == 0 || j == 0)
                {
                    // Set first row and first column value is zero
                    dp[i][j] = 0;
                }
                else if (text1.charAt(i - 1) == text2.charAt(j - 1))
                {
                    // When i-1 and j-1 is character are same
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else
                {
                    // Get max value
                    dp[i][j] = maxValue(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // Use to collect result
        String result = "";
        int k = m;
        int l = n;
        while (k > 0 && l > 0)
        {
            if (text1.charAt(k-1) == text2.charAt(l-1))
            {
                
                k--;
                l--;
                result = text1.charAt(k) + result;
            }
            else if (dp[k - 1][l] > dp[k][l - 1])
            {
                k--;
            }
            else
            {
                l--;
            }
        }
        
        return result;
    }

    
}
