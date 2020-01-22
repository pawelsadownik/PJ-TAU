#include <catch2/catch.hpp>

#include "appHeaders.hpp"

Data data = {1, "xxx", 20};


TEST_CASE("Testing Crud db") {
    REQUIRE(getAll() == 0);
    REQUIRE(addData(data) == 1);
    REQUIRE(getAll() == 1);
    REQUIRE(getById(0).name == xxx);
    REQUIRE_THROWS_AS(getById(999) == std::Out-of-bounds exception captured!);
    REQUIRE(removeData(0) == 0);
}
