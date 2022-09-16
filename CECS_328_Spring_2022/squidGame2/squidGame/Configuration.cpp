#include "Configuration.h"

Configuration::Configuration(int x)
{
	lines.resize(x, vector<int>());
}

Configuration::Configuration(const Configuration& other)
{
	lines = other.lines;
}

vector<Configuration> Configuration::increment(const Configuration& current)
{
	int maxValue = 0;
	for (int i = 0; i < current.lines.size(); i++) maxValue += (int)current.lines.at(i).size();
	int newValue = maxValue + 1;

	vector<Configuration> answer;
	for (int line = 0; line < current.lines.size(); line++) {
		Configuration potential = current;
		if (potential.lines.at(line).empty()) {
			potential.lines.at(line).push_back(newValue);
			answer.push_back(potential);
			break;
		}
		if (isSquare(newValue + potential.lines.at(line).back())) {
			potential.lines.at(line).push_back(newValue);
			answer.push_back(potential);
		}
	}
	return answer;
}

bool Configuration::isSquare(int x)
{
	int sq = (int)sqrt(x);
	return (sq * sq == x);
}
