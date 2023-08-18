import pandas as pd
df = pd.read_csv ('/home/esak/2K24/LearnBackendDevelopment/CogDemo/demoProject/Data/train.csv')
df.to_json ('/home/esak/2K24/LearnBackendDevelopment/CogDemo/demoProject/Data/train.json', orient = "records", date_format = "epoch", double_precision = 10, force_ascii = True, date_unit = "ms", default_handler = None)