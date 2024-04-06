import re
import os
from os.path import join, isfile


def list_directory_contents(path):
    return [file for file in os.listdir(path)]


class TestLoader:
    def __init__(self, root_directory):
        self.root_directory = root_directory
    
    def load_types(self):
        directories = list_directory_contents(self.root_directory)
        self.types = directories
        return self.types

    def set_type(self, type_tmp):
        self.type_name = type_tmp

    def get_type(self):
        return self.type_name

    def load_sizes(self):
        directories = list_directory_contents(join(self.root_directory, self.type_name))
        self.sizes = [d for d in directories]
        return self.sizes

    def set_size(self, size_tmp):
        self.size = size_tmp

    def get_size(self):
        return self.size
    
    def load_ranges(self):
        directories = list_directory_contents(join(self.root_directory, self.type_name, self.size))
        self.ranges = [d for d in directories]
        return self.ranges
    
    def set_range(self, range_tmp):
        self.range = range_tmp
    
    def get_range(self):
        return self.range
    
    def load_tests(self):
        directories = list_directory_contents(join(self.root_directory, self.type_name, self.size, self.range))
        self.tests = [d for d in directories]
        self.tests = self.tests[0:2]  # limiting to first two tests
        return self.tests
    
    def set_test(self, test_tmp):
        self.test = test_tmp

    def get_test(self):
        return self.test

    def read_kp_file(self):
        file_path = join(self.root_directory, self.type_name, self.size, self.range, self.test)
        with open(file_path, 'r') as file:
            lines = file.readlines()
        
        n = int(lines[1].strip())
        c = int(lines[2].strip())
        
        values = []
        weights = []
        for i in range(4, n+4):
            p, w = map(int, lines[i].strip().split())
            values.append(p)
            weights.append(w)
        
        return n, c, values, [weights]
    
    def get_all_info(self):
        info = dict()

        info['type'] = self.type_name
        info['size'] = self.size
        info['range'] = self.range
        info['test'] = self.test

        return info
