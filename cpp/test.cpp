#define CATCH_CONFIG_MAIN

#include <catch.hpp>

#include "appHeaders.hpp";

Data dataTest = {1, "xxx", 20};

TEST_CASE("Testing getting all elements") {
    REQUIRE(getAll().size() == 0);
}
TEST_CASE("Testing adding data to db") {
    REQUIRE(addData(dataTest) == 1);
}
TEST_CASE("Testing if size changed after add") {
    REQUIRE(getAll().size() == 1);
}

TEST_CASE("Testing getting by ID") {
    REQUIRE(getById(0).name == "xxx");
}

TEST_CASE("Testing  exception with invalid id") {
    REQUIRE_THROWS_WITH(getById(999), "Out-of-bounds exception captured!");
}

TEST_CASE("Testing Removing item from db") {
    REQUIRE(removeData(0) == 0);
}
