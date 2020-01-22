#include "appHeaders.hpp"
using namespace std;


map<int, Data> getAll(){
  return db;
};

Data getById(int id){
  return db[id];
};

int addData(Data data){
  db.insert({data.id, data});
  return db.size();
};

int removeData(int id){
  db.erase(id);
  return db.size();
};
