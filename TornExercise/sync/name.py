from collections import namedtuple

WorkSpace = namedtuple('WorkSpace', 'client user location')

# Configure perforce work space configuration here
work_spaces = [WorkSpace('sriee_ws', 'sriee_sathiiss', '//depot/Global_Performance_Unit/Tools/Automation/...'),
               WorkSpace('performance', 'sriee_sathiiss', '//depot/Global_Performance_Unit/...')
               ]

for item in work_spaces:
    print('p4.client={}, p4.user={}, p4sync(\'{}\')'.format(item.client, item.user, item.location))
