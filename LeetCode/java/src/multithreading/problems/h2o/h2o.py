import threading
import time
import logging
import random
import Queue

logging.basicConfig(level=logging.DEBUG,
                    format='(%(threadName)-9s) %(message)s',)

q_h = Queue.Queue()
q_o = Queue.Queue()

class H2OGenerator(object):
    def generate(self):
        pass
        # if q_h.qsize() >= 2 and q_o.qsize >= 1:
        #     q_h.get()
        #     q_h.get()
        #     q_o.get()

class HThread(threading.Thread, H2OGenerator):
    def __init__(self, group=None, target=None, name=None,
                 args=(), kwargs=None, verbose=None):
        super(HThread,self).__init__()
        self.target = target
        self.name = name

    def run(self):
        # while True:
        with cv_h:
            q_h.put(True)
            if q_h.qsize() >= 2 and q_o.qsize() >= 1:
                logging.info('q_h:{}, q_o:{}'.format(q_h.qsize(), q_o.qsize()))
                q_h.get()
                q_h.get()
                q_o.get()
                cv_o.notify()
            else:
                logging.info(self.name + ' wait')
                cv_h.wait()
                logging.info(self.name + ' finish wait')
        logging.info(self.name + ' finish')
        return


class OThread(threading.Thread, H2OGenerator):
    def __init__(self, *args, **kwargs):
        super(OThread,self).__init__(*args, **kwargs)
        return

    def run(self):
        # while True:
        with cv_o:
            q_o.put(True)
            if q_h.qsize() >= 2 and q_o.qsize() >= 1:
                logging.info('q_h:{}, q_o:{}'.format(q_h.qsize(), q_o.qsize()))
                q_h.get()
                q_h.get()
                q_o.get()
                cv_h.notify()
                cv_h.notify()
                logging.info(self.name + ' finish')
                return
            else:
                cv_o.wait()

if __name__ == '__main__':
    lock = threading.RLock()
    cv_h = threading.Condition(lock)
    cv_o = threading.Condition(lock)
    h1 = HThread(name='h1', args=(cv_h, cv_o))
    h2 = HThread(name='h2', args=(cv_h, cv_o))
    h3 = HThread(name='h3', args=(cv_h, cv_o))
    h4 = HThread(name='h4', args=(cv_h, cv_o))
    o1 = OThread(name='o1', args=(cv_h, cv_o))
    o2 = OThread(name='o2', args=(cv_h, cv_o))

    h1.start()
    h2.start()
    h3.start()
    h4.start()
    o1.start()
    o2.start()