import argparse
import sys
import os
import pandas as pd
from sklearn.linear_model import LogisticRegression, LinearRegression
from joblib import dump, load
from sklearn import metrics
from sklearn.metrics import recall_score
import math
from sklearn.preprocessing import StandardScaler, RobustScaler
from sklearn.ensemble import RandomForestClassifier
from sklearn.neighbors import KNeighborsRegressor


# Hàm để chạy quá trình huấn luyện
def run_train(train_dir, dev_dir, model_dir):
    # Tạo thư mục cho mô hình nếu nó chưa tồn tại
    os.makedirs(model_dir, exist_ok=True)

    # Đường dẫn đến các tệp dữ liệu
    train_file = os.path.join(train_dir, 'train.csv')
    # Đọc dữ liệu huấn luyện và phát triển
    train_data = pd.read_csv(train_file)
    #original_vm = train_data['Vehicle_Mass']
    X_train = train_data.drop(['CoVeh_trqAcs_100ms','Clth_st_100ms', 'CoEng_st_100ms', 
                          'Com_rTSC1VRVCURtdrTq_100ms', 'Com_rTSC1VRRDTrqReq_100ms', 'Vehicle_Mass','RoadSlope_100ms'], axis=1)
    X_train['Binning_VehV_v_100ms_RngMod_trqCrSmin_100ms'] = 1 / X_train['VehV_v_100ms'] * X_train['RngMod_trqCrSmin_100ms']
    scaler = RobustScaler().fit(X_train)
    X_train = scaler.transform(X_train)
   

    Y_train_slope = train_data['RoadSlope_100ms']
    Y_train_mass = train_data['Vehicle_Mass']

    model_mass = RandomForestClassifier(n_estimators = 200, bootstrap= False, random_state=42)
    model_mass.fit(X_train, Y_train_mass)

    model_slope = KNeighborsRegressor(n_neighbors=1, p=1, metric='manhattan', weights='distance')
    model_slope.fit(X_train, Y_train_slope)
    # Lưu mô hình
    model_mass_path = os.path.join(model_dir, 'trained_mass.joblib')
    model_slope_path = os.path.join(model_dir, 'trained_slope.joblib')
    scaler_path = os.path.join(model_dir, 'scaler.joblib')
   
    dump(scaler,scaler_path)
    dump(model_mass, model_mass_path)
    dump(model_slope, model_slope_path)


# Hàm để chạy quá trình dự đoán
def run_predict(model_dir, input_dir, output_path):
    # Đường dẫn đến mô hình và dữ liệu đầu vào
    model_mass_path = os.path.join(model_dir, 'trained_mass.joblib')
    model_slope_path = os.path.join(model_dir, 'trained_slope.joblib')
    # scaler_mass_path = os.path.join(model_dir, 'scaler_mass.joblib')
    scaler_path = os.path.join(model_dir, 'scaler.joblib')
    input_file = os.path.join(input_dir, 'test.csv')

    # Tải mô hình
    model_mass = load(model_mass_path)
    model_slope = load(model_slope_path)

    # Đọc dữ liệu kiểm tra
    test_data = pd.read_csv(input_file)

    X_test = test_data.drop(['CoVeh_trqAcs_100ms','Clth_st_100ms', 'CoEng_st_100ms', 
                          'Com_rTSC1VRVCURtdrTq_100ms', 'Com_rTSC1VRRDTrqReq_100ms'], axis=1)
    # scaler_mass = load(scaler_mass_path)
    X_test['Binning_VehV_v_100ms_RngMod_trqCrSmin_100ms'] = 1 / X_test['VehV_v_100ms'] * X_test['RngMod_trqCrSmin_100ms']
    scaler = load(scaler_path)
    
    # Chuẩn bị dữ liệu kiểm tra
    X_test = scaler.transform(X_test)
    
    # Thực hiện dự đoán
    y_pred_slope = model_slope.predict(X_test)
    y_pred_mass = model_mass.predict(X_test)

    # Lưu kết quả dự đoán
    pd.DataFrame({'RoadSlope_100ms':y_pred_slope,'Vehicle_Mass':y_pred_mass}).to_json(output_path, orient='records', lines=True)


# Hàm chính để xử lý lệnh từ dòng lệnh
def main():
    # Tạo một parser cho các lệnh từ dòng lệnh
    parser = argparse.ArgumentParser()
    subparsers = parser.add_subparsers(dest='command')

    # Tạo parser cho lệnh 'train'
    parser_train = subparsers.add_parser('train')
    parser_train.add_argument('--train_dir', type=str)
    parser_train.add_argument('--dev_dir', type=str)
    parser_train.add_argument('--model_dir', type=str)

    # Tạo parser cho lệnh 'predict'
    parser_predict = subparsers.add_parser('predict')
    parser_predict.add_argument('--model_dir', type=str)
    parser_predict.add_argument('--input_dir', type=str)
    parser_predict.add_argument('--output_path', type=str)

    # Xử lý các đối số nhập vào
    args = parser.parse_args()

    # Chọn hành động dựa trên lệnh
    if args.command == 'train':
        run_train(args.train_dir, args.dev_dir, args.model_dir)
    elif args.command == 'predict':
        run_predict(args.model_dir, args.input_dir, args.output_path)
    else:
        parser.print_help()
        sys.exit(1)

# Điểm khởi đầu của chương trình
if __name__ == "__main__":
    main()