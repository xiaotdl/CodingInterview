# Question:
# https://stackoverflow.com/questions/39985191/algorithm-to-merge-contacts
contacts = {
    "A1": ["a1", "a2"],
    "A2": ["b1", "a2"],
    "A3": ["c1"],
    "A4": ["c1", "d1"],
    "A5": ["b1", "e2"],
}

class UnionFindSet:
    def __init__(self, n):
        self.parents = list(0 for x in range(n + 1))
        for x in range(1, n + 1):
            self.parents[x] = x

    def find(self, x):
        if (self.parents[x] == x):
            return x
        self.parents[x] = self.find(self.parents[x])
        return self.parents[x]

    def union(self, a, b):
        rootA = self.find(a)
        rootB = self.find(b)
        if (rootA != rootB):
            self.parents[rootA] = rootB

class Solution:
    def run(self, contacts):
        # get all emails
        all_emails = set()
        for contact, emails in contacts.items():
            for email in emails:
                all_emails.add(email)
        all_emails = list(all_emails)

        # union all related emails
        self.ufs = UnionFindSet(len(all_emails))
        for contact, emails in contacts.items():
            for i in range(1, len(emails)):
                email_id1 = all_emails.index(emails[i - 1]) + 1
                email_id2 = all_emails.index(emails[i]) + 1
                self.ufs.union(email_id1, email_id2)

        # find related contact by find email
        grouped_contacts = {} # email_id2contacts
        for contact, emails in contacts.items():
            email_id = all_emails.index(emails[0]) + 1
            root_email_id = self.ufs.find(email_id)
            if root_email_id not in grouped_contacts:
                grouped_contacts[root_email_id] = []
            grouped_contacts[root_email_id].append(contact)
        return [grouped_contacts[email_id] for email_id in grouped_contacts]


print Solution().run(contacts)
# >>>
# [['A1', 'A2', 'A5'], ['A3', 'A4']]
