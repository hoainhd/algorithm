package p01_sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring with K Distinct Characters (medium)
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 */
class A04LongestSubstringKDistinct {
	public static int findLength(String str, int k) {
		if (str == null || str.length() == 0 || str.length() < k) {
			throw new IllegalArgumentException();
		}

		int windowStart = 0, maxLength = 0;
		Map<Character, Integer> charFrequencyMap = new HashMap<>();
		// in the following loop we'll try to extend the range [windowStart, windowEnd]
		for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
			char rightChar = str.charAt(windowEnd);
			charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
			// shrink the sliding window, until we are left with 'k' distinct characters in
			// the frequency map
			while (charFrequencyMap.size() > k) {
				char leftChar = str.charAt(windowStart);
				charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
				if (charFrequencyMap.get(leftChar) == 0) {
					charFrequencyMap.remove(leftChar);
				}
				windowStart++; // shrink the window
			}
			maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
		}

		return maxLength;
	}

	public static void main(String[] args) {
		System.out.println("Length of the longest substring: " + A04LongestSubstringKDistinct.findLength("araaci", 2));
		System.out.println("Length of the longest substring: " + A04LongestSubstringKDistinct.findLength("araaci", 1));
		System.out.println("Length of the longest substring: " + A04LongestSubstringKDistinct.findLength("cbbebi", 3));
	}
}

/*
Time Complexity: O(N). The outer for loop runs for all characters and the inner while loop processes each character only once, therefore the time complexity of the algorithm will be O(N+N) which is asymptotically equivalent to O(N).
Space Complexity: O(K), as we will be storing a maximum of �K+1� characters in the HashMap.
*/