from P4 import P4, P4Exception
import traceback


p4 = P4()
p4.port = 'ssl:p4cc.ges.symantec.com:1666'
p4.user = 'sriee_sathiiss'
p4.client = 'sriee_ws'

try:
    p4.connect()
    info = p4.run('info')
    for key in info[0]:
        print(key, '=', info[0][key])
except P4Exception:
    traceback.print_exc()
finally:
    p4.disconnect()