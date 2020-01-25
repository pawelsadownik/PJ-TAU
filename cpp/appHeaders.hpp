#ifndef appHeeaders.hpp
#define appHeeaders.hpp
#include <iostream>
#include <string>
#include <map>
using namespace std;

struct Data
{
    int id;
    std::string name;
    int duration;
};

std::map<int, Data> db;

map<int, Data> getAll();
Data getById(int id);
int addData(Data data);
int removeData(int id);

#endif
