#pragma once
#include <vector>

using namespace std;

class Configuration
{
public:
	Configuration(int);
	Configuration(const Configuration&);
	vector<vector<int>> lines;

	static vector<Configuration> increment(const Configuration&);
	static bool isSquare(int);
};
