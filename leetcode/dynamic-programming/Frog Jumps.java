/*
There are N blocks, numberred from 0 to N-1 , arranged in a row , A couple of frogs were sitting together on one block when they had a terrible quarrel. Now they want to jump away from one another so that the distance between them will be as large as possible. The distance between blocks numbberd J and K, where J<=k, is cmputed as K-J+1, The frog can only jump up, meaning that they can move from one block to another only if two blocks are adjaccent and the second block is of the same or greater height as the first What is the longest distance that thay can possibly create between each other, if they also chose to sit on the optimal startging block initially?

Example 1:
given blocks = [2,6,8,5] -> return 3,
if starting from blocks[0], the first frog can stay where it is and the second frog can jump to blocks[2](but not blocks[3])

Example 2:
blocks = [1,5,5,2,6], return 4;
if starting from blocks[3] the first frog can jump to blocks[1], but not blocks[0] and the second frog can jump to blocks[4]

Example 3:
Blocks[1,1] return 2
If starting from blocks[1] , the first frog can jump to blocks[0] and the second frog cn stay where it it, Starting from blocks[0] would result in the same distance.
'''
method signature python

def distance(blocks):

*/


private static int solve(int[] nums) {
	int n = nums.length;
	int[] arrF = new int[n], arrB = new int[n];
	int res = 0;
	Arrays.fill(arrF, 1);
	Arrays.fill(arrB, 1);
	for(int i=1;i<n;i++) {
		if(nums[i] <= nums[i-1])
			arrF[i] = arrF[i-1] + 1;
	}
	for(int i=n-2;i>=0;i--) {
		if(nums[i] <= nums[i+1])
			arrB[i] = arrB[i+1] + 1;
	}
	for(int i=0;i<n;i++) {
		res = Math.max(res, arrF[i] + arrB[i] - 1);
	}
	return res;
}
