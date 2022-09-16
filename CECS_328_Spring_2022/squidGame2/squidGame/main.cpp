#include "Configuration.h"
#include <iostream>

using namespace std;

int main(int argc, char* argv[]) {
	Configuration configuration(5);
	vector<Configuration> level; level.push_back(configuration);
	for (int i = 0; i < 10; i++) {
		vector<Configuration> nextLevel;
		for (int j = 0; j < level.size(); j++) {
			vector<Configuration> result = Configuration::increment(level.at(j));
			for (int k = 0; k < result.size(); k++) nextLevel.push_back(result.at(k));
		}
		swap(level, nextLevel);
	}

	vector<Configuration> answer;
	for (int i = 0; i < level.size(); i++) {
		bool valid = true;
		for (int j=0; j<level.at(i).lines.size(); j++)
			if (level.at(i).lines.at(j).empty())
			{
				valid = false; break;
			}
		if (valid) answer.push_back(level.at(i));
	}

	cout << "Pause" << endl;
}