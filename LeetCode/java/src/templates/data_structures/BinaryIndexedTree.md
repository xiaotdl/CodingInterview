BIT解决问题：前n项和

nums[n]
BIT[n+1]

i -= i & -i;
i += i & -i;

	void update(int i, int val) {
		int diff = val - nums[i];
		nums[i] = val;
        i++;
        while (i <= n) {
            BIT[i] += diff;
            i += (i & -i); // find parent BIT
        }
	}

    int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i); // find child BIT
        }
        return sum;
    }
