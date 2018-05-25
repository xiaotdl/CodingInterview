package get_highest_answer_rate_question;

/**
 * Created by Xiaotian on 5/5/18.
 */
public class Solution {
}

/* SQL Query:

    SELECT
        question_id AS 'survey_log'
    FROM
        survey_log
    GROUP BY question_id
    ORDER BY COUNT(answer_id) / COUNT(*) DESC
    LIMIT 1;

*/

