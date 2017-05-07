import subprocess
home_dir="/home/marcin"
input_dir="{0}/spark-training-repo/training/exercises/input".format(home_dir)
output_dir="{0}/spark-training/exercises/input".format(home_dir)
subprocess.check_output("cp -r {0}/* {1}/".format(input_dir, output_dir),shell=True)

