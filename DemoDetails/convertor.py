import pandas as pd
df = pd.read_csv ('/home/esak/Downloads/archive/new.csv')
df.to_json('/home/esak/Downloads/archive/new.json', orient = "records", date_format = "epoch", double_precision = 10, force_ascii = True, date_unit = "ms", default_handler = None)