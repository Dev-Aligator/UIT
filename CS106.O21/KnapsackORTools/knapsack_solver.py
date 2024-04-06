from ortools.algorithms.python import knapsack_solver
import time
from test_loader import *

already_solved = ["04AlmostStronglyCorrelated", "12Circle", "00Uncorrelated", "11ProfitCeiling", "05SubsetSum", "09SpannerStronglyCorrelated", "10MultipleStronglyCorrelated", "07SpannerUncorrelated", "03InverseStronglyCorrelated"]
def main():
    with open("results/results.csv", "a") as result_file:
        result_file.write("type, size, range, name, total_value, total_weight, running_time, isOptimalSolution\n")

    file_path = "kplib_test"
    test_loader_instance = TestLoader(file_path)

    solver = knapsack_solver.KnapsackSolver(
        knapsack_solver.SolverType.KNAPSACK_MULTIDIMENSION_BRANCH_AND_BOUND_SOLVER,
        "KnapsackExample",
    )

    time_limit = 30
    solver.set_time_limit(time_limit)

    for test_type in test_loader_instance.load_types():
        test_loader_instance.set_type(test_type)
        if test_loader_instance.get_type() in already_solved:
            continue
        for test_size in test_loader_instance.load_sizes():
            test_loader_instance.set_size(test_size)
            for test_range in test_loader_instance.load_ranges():
                test_loader_instance.set_range(test_range)
                for test_index in test_loader_instance.load_tests():
                    test_loader_instance.set_test(test_index)
                    n, c, values, weights = test_loader_instance.read_kp_file()
                    capacities = [c]

                    print(test_loader_instance.get_type(), test_loader_instance.get_size(), test_loader_instance.get_range(), test_loader_instance.get_test())
                    
                    solver.init(values, weights, capacities)
                    start_time = time.time()
                    computed_value = solver.solve()
                    duration = abs((time.time() - start_time))
                    is_optimal = True if time_limit > duration + 1 else False

                    packed_items = []
                    packed_weights = []
                    total_weight = 0

                    print('Total value =', computed_value)
                    for i in range(len(values)):
                        if solver.best_solution_contains(i):
                            packed_items.append(i)
                            packed_weights.append(weights[0][i])
                            total_weight += weights[0][i]

                    print('Total weight:', total_weight)
                    print('Packed items:', packed_items)
                    print('Packed_weights:', packed_weights)
                    print('Running Time:', duration)
                    print('Is Optimal Solution:', is_optimal)

                    info = test_loader_instance.get_all_info()

                    with open("results/results.csv", "a+") as result_file:
                        result_file.write(f"{test_loader_instance.get_type()}, {test_loader_instance.get_size()}, {test_loader_instance.get_range()}, {test_loader_instance.get_test()}, {computed_value}, {total_weight}, {duration}, {is_optimal}\n")

                    txt_path = f"results/types/{info['type']}.txt"

                    with open(txt_path, "a+") as txt_file:
                        txt_file.write(f"{info['size']}/{info['range']}/{info['test']}\n")
                        txt_file.write(f"Number of items: {len(packed_items)}\n")
                        txt_file.write("Packed_items:\n")
                        txt_file.write(" ".join(str(v) for v in packed_items) + "\n")
                        txt_file.write("Packed_weights:\n")
                        txt_file.write(" ".join(str(w) for w in packed_weights) + "\n")

if __name__ == '__main__':
    main()
