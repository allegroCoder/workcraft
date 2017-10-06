script_file=${test_dir}/help.js

[[ -e $script_file ]] || error "Script file ${script_file} is missing"

./workcraft -nogui -dir:${test_dir} -exec:${script_file} >${log_file}
