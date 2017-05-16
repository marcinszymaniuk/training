import subprocess
import sys
home_dir="/home/marcin"

number=int(sys.argv[1])
isSolution=sys.argv[2]=="True"
numberToPackageName = {1:"first", 2:"second"}


def reset_dir(from_dir, to_dir):
	print "reseting {0} to {1}".format(to_dir, from_dir)
	subprocess.check_output("rm -rf {0}/*".format(to_dir),shell=True)
	subprocess.check_output("cp -r {0}/* {1}/".format(from_dir, to_dir),shell=True)


def resetAll():
	input_from="{0}/spark-training-repo/training/exercises/input".format(home_dir)
	input_to="{0}/spark-training/exercises/input".format(home_dir)

	src_from="{0}/spark-training-repo/training/exercises/src".format(home_dir)
	src_to="{0}/spark-training/exercises/src".format(home_dir)

	scripts_from="{0}/spark-training-repo/training/scripts".format(home_dir)
	scripts_to="{0}/spark-training/scripts".format(home_dir)
	
	reset_dir(input_from, input_to)
	reset_dir(src_from, src_to)
	reset_dir(scripts_from, scripts_to)

        subprocess.check_output("cp {0}/spark-training-repo/training/exercises/build.gradle {0}/spark-training/exercises/".format(home_dir),shell=True)
        subprocess.check_output("cp {0}/spark-training-repo/training/exercises/run-local.sh {0}/spark-training/exercises/".format(home_dir),shell=True)

def resetExercise(exerciseNo, solution=False):
        input_from="{0}/spark-training-repo/training/exercises/input/{1}".format(home_dir, exerciseNo)
        input_to="{0}/spark-training/exercises/input/{1}".format(home_dir, exerciseNo)

	resetTo = "solutions" if solution else "exercises"
	print resetTo
        src_from="{0}/spark-training-repo/training/{1}/src/main/scala/com/tantusdata/training/{2}".format(home_dir,resetTo, numberToPackageName.get(exerciseNo))
        src_to="{0}/spark-training/exercises/src/main/scala/com/tantusdata/training/{1}".format(home_dir,numberToPackageName.get(exerciseNo))

	test_from="{0}/spark-training-repo/training/scripts".format(home_dir)
        test_to="{0}/spark-training/scripts".format(home_dir)

        reset_dir(input_from, input_to)
        reset_dir(src_from, src_to)
        reset_dir(test_from, test_to)
	


#print isSolution
resetAll()
for i in range(1,number+1):
	resetExercise(i, isSolution)
