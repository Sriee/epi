from datetime import datetime
import pytz


def convert_time(diff_time):
    sec = diff_time.days * 24 * 60 * 60
    hrs = int((diff_time.seconds + sec) / 3600)
    mns = int(((diff_time.seconds + sec) % 3600) / 60)
    return str(hrs) + ":" + str(mns).zfill(2)


time_created = datetime.strptime('2018-03-16 10:29:12', '%Y-%m-%d %H:%M:%S')
time_completed = datetime.now(pytz.timezone('US/Pacific'))

print(convert_time(time_completed.replace(tzinfo=None) - time_created))

# Example implementation check for decorator
def salutation(func):
    def salutation_wrapper(**kwargs):
        name = 'Mr.' + kwargs['first_name'] + ' ' + kwargs['last_name'] + ' Ph.D'
        return func(name)

    return salutation_wrapper


@salutation
def main(name):
    print(name)


if __name__ == '__main__':
    main(first_name='Tom', last_name='Cruise')
